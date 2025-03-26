package com.example.imc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.confirma.setOnClickListener {
            val peso = binding.pesoUsuario.text.toString().toDouble()
            val altura = binding.alturaUsuario.text.toString().toDouble()
            val idade = binding.idadeUsuario.text.toString().toInt()
            val nome = binding.nomeUsuario.text.toString()

            if (peso != null && altura != null) {

            } else {
                binding.feedback.text = "ERRO: Peso e/ou altura não são válidos."
            }
        }
    }

    private fun calcIMC(nome: String, idade: Int, altura: Double, peso: Double) {
        val user = User(nome, idade, peso, altura )
        val result = user.calcIMC()
        val desc = user.classif()

        binding.feedback.text = "Olá, %s! Sua idade é %d e seu IMC é: %.2f, te colocando na classificação: %s".format(nome,idade,result,desc)
    }

}
class User(
    val nome: String,
    val idade: Int,
    val peso: Double,
    val altura: Double
) {
    fun calcIMC():Double = (peso / (altura*altura))
    fun classif(): String {
        val imc = calcIMC()

        return when {
            imc < 18.5 -> "Abaixo do peso."
            imc < 24.9 -> "Peso normal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Obesidade: Grau I"
            imc < 39.9 -> "Obesidade: Grau II"
            else -> "Obesidade: Grau III"
        }
    }
}
