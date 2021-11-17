package com.kumail.cakes.ui.cakeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kumail.cakes.R
import com.kumail.cakes.databinding.FragmentCakesListBinding
import com.kumail.cakes.util.setToolbarTitle
import com.kumail.cakes.util.showErrorDialog
import com.kumail.cakes.util.showPopupDialog
import com.kumail.cakes.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by kumailhussain on 16/11/2021.
 */
@AndroidEntryPoint
class CakesListFragment : Fragment() {

    @Inject
    lateinit var cakesListAdapter: CakesListAdapter

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var refreshLayout: SwipeRefreshLayout

    private var _binding: FragmentCakesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCakesListBinding.inflate(layoutInflater)

        this.setToolbarTitle(getString(R.string.list_of_cakes))
        refreshLayout = binding.swipeRefresh
        setupCakesList(binding.rvCakes)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupSwipeToRefresh(binding.swipeRefresh)
    }

    private fun setupObservers() {
        mainViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.swipeRefresh.isRefreshing = isLoading
        })

        mainViewModel.errorMessage.observe(viewLifecycleOwner, { errorMessage ->
            showErrorDialog(requireContext(), errorMessage) { _, _ -> mainViewModel.getCakesList() }
        })

        mainViewModel.cakesList.observe(viewLifecycleOwner, { cakes ->
            cakesListAdapter.submitList(cakes)
        })
    }

    private fun setupSwipeToRefresh(swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.getCakesList()
        }
    }

    private fun setupCakesList(listView: RecyclerView) {
        cakesListAdapter.setOnItemClickListener { title, description ->
            showPopupDialog(
                requireContext(),
                title,
                description
            )
        }
        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.adapter = cakesListAdapter
        listView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}