package com.kata.tictactoe.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.kata.tictactoe.R
import com.kata.tictactoe.adapter.GameAdapter
import com.kata.tictactoe.databinding.FragmentGameBinding
import com.kata.tictactoe.ui.viewmodel.GameViewModel
import com.kata.tictactoe.utils.GameState
import com.kata.tictactoe.utils.alertDialog
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
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        initializePlayerNames()
        observeViewModel()
        initializeRecyclerView()
        addResetButtonClick()
    }

    private fun initializePlayerNames() {
        val player1 = args.player1Name
        val player2 = args.player2Name

        binding.player1Name.text = player1
        binding.player2Name.text = player2
        viewModel.initializePlayerNames(player1, player2)
    }

    private fun observeViewModel() {
        viewModel.gameState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is GameState.InProgress -> {
                    gameAdapter.updateGameBoardData(
                        state.cellPosition,
                        state.cellValue
                    )
                    updateResetButtonVisibility(View.VISIBLE)
                }
                is GameState.Result -> showAlert(state.message)
            }
        }
    }

    private fun updateResetButtonVisibility(viewVisibility: Int) {
        binding.resetBtn.visibility = viewVisibility
    }

    private fun showAlert(message: String) {
        requireContext().alertDialog(
            message = message,
            positiveText = getString(R.string.ok_btn_label)
        ) {
            resetToInitialState()
        }
    }

    private fun resetToInitialState() {
        viewModel.resetBoard()
        gameAdapter.clearCells()
        updateResetButtonVisibility(View.GONE)
    }

    private fun initializeRecyclerView() {
        binding.gameRecyclerView.apply {
            adapter = gameAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun addResetButtonClick() {
        binding.resetBtn.setOnClickListener {
            requireContext().alertDialog(
                message = getString(R.string.reset_msg),
                positiveText = getString(R.string.reset),
                negativeText = getString(R.string.cancel_text)
            ) {
                resetToInitialState()
            }
        }
    }

    private fun onItemClicked(position: Int) {
        viewModel.updateBoard(position)
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}