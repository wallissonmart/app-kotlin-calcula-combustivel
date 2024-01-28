package com.example.calculacombustivel

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculacombustivel.databinding.FragmentHomeBinding
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {

        binding.editTextDistancia.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable?) {
                val inputText = editable.toString()
                if (inputText.isNotEmpty() && !inputText.endsWith(" km")) {
                    binding.editTextDistancia.setText("$inputText km")
                    binding.editTextDistancia.setSelection(binding.editTextDistancia.text.length - 3)
                }
            }
        })

        binding.editTextConsumoMedio.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable?) {
                val inputText = editable.toString()
                if (inputText.isNotEmpty() && !inputText.endsWith(" km")) {
                    binding.editTextConsumoMedio.setText("$inputText km")
                    binding.editTextConsumoMedio.setSelection(binding.editTextConsumoMedio.text.length - 3)
                }
            }
        })

        binding.buttonCalcularHome.setOnClickListener {
            val distanciaPercorrida = binding.editTextDistancia.text
            val consumoMedioVeiculo = binding.editTextConsumoMedio.text.toString().replace("km", "")
            val precoLitro = binding.editTextPrecoLitro.unMasked
            val resultado = binding.textResultadoHome

            val precoLitroFormatted = "${precoLitro.substring(0, 1)}.${precoLitro.substring(1)}"

            val valorGastoKM = precoLitroFormatted.toDouble() / consumoMedioVeiculo.toDouble()
            val valorTotal =
                valorGastoKM * distanciaPercorrida.toString().replace("km", "").toDouble()

            val valorTotalArredondado = (valorTotal * 100.0).roundToInt() / 100.0

            resultado.text = "R$ ${valorTotalArredondado.toString().replace(".", ",")}"
        }
    }
}