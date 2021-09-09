package com.kata.tictactoe.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.kata.tictactoe.adapter.GameAdapter
import com.kata.tictactoe.databinding.FragmentGameBinding
import com.kata.tictactoe.ui.viewmodel.GameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val args: GameFragmentArgs by navArgs()
    private val viewModel: GameViewModel by viewModel()

    private val gameAdapter: GameAdapter by lazy {
        GameAdapter { position ->
            onItemClicked(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializePlayerNames()
        observeViewModel()
        initializeRecyclerView()
    }

    private fun initializePlayerNames() {
        val player1 = args.player1Name
        val player2 = args.player2Name

        binding.player1Name.text = player1
        binding.player2Name.text = player2
    }

    private fun observeViewModel() {
        viewModel.gameState.observe(viewLifecycleOwner) {

        }
    }

    private fun initializeRecyclerView() {
        binding.gameRecyclerView.apply {
            adapter = gameAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun onItemClicked(position: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}