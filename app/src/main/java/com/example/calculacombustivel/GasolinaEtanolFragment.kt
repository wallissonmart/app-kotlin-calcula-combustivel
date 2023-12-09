package com.example.calculacombustivel

import CombustivelController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculacombustivel.databinding.FragmentGasolinaEtanolBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
class GasolinaEtanolFragment : Fragment() {

    private lateinit var binding: FragmentGasolinaEtanolBinding
    private lateinit var combustivelController: CombustivelController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGasolinaEtanolBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        combustivelController = CombustivelController(requireContext())

        setupListeners()
    }

    private fun setupListeners() {

        binding.buttonLimpar.setOnClickListener {
            binding.editTextGasolina.editableText.clear()
            binding.editTextEtanol.editableText.clear()
        }

        binding.buttonSalvar.setOnClickListener {
            val precoGasolina = binding.editTextGasolina.unMasked
            val precoEtanol = binding.editTextEtanol.unMasked
            val nowDate = obterDataDeHoje()

            val precoEtanolFormatted = "${precoEtanol.substring(0, 1)}.${precoEtanol.substring(1)}"
            val precoGasolinaFormatted =
                "${precoGasolina.substring(0, 1)}.${precoGasolina.substring(1)}"
            println(precoEtanolFormatted)
            combustivelController.inserirRegistro(
                precoEtanolFormatted.toDouble(),
                precoGasolinaFormatted.toDouble(),
                nowDate
            )
        }

        binding.buttonCalcular.setOnClickListener {
            val precoGasolina = binding.editTextGasolina.unMasked
            val precoEtanol = binding.editTextEtanol.unMasked

            val fatorEficiencia = precoEtanol.toDouble() / precoGasolina.toDouble()

            if (fatorEficiencia < 0.7) {
                binding.textResultado.text = "Melhor combustível é Etanol"
            } else {
                binding.textResultado.text = "Melhor combustível é Gasolina"
            }
        }
    }

    private fun obterDataDeHoje(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}