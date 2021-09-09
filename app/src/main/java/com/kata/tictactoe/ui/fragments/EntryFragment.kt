package com.kata.tictactoe.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kata.tictactoe.R
import com.kata.tictactoe.databinding.FragmentEntryBinding
import com.kata.tictactoe.ui.viewmodel.EntryViewModel
import com.kata.tictactoe.utils.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntryFragment : Fragment() {

    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!

    private var player2Name: String = ""
    private var player1Name: String = ""
    private val viewModel: EntryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.areFieldsValid.observe(viewLifecycleOwner) { isValid ->
            when (isValid) {
                true -> navigate()
                false -> showErrorMessage()
            }
        }

        binding.playBtn.setOnClickListener {
            player1Name = binding.etPlayer1.text.toString()
            player2Name = binding.etPlayer2.text.toString()
            viewModel.checkIfFieldsAreValid(player1Name, player2Name)
        }
    }

    private fun navigate() {
        val actions = EntryFragmentDirections.actionEntryFragmentToGameFragment(
            player1Name, player2Name
        )
        findNavController().navigate(actions)
    }

    private fun showErrorMessage() {
        binding.root.showSnackBar(
            getString(R.string.enter_all_fields),
            true
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}