package br.com.ocram.sorteionumerico

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import kotlin.random.Random

val numerosSorteados: ArrayList<Int> = arrayListOf()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val botao = findViewById<Button>(R.id.button)
        botao.setText(R.string.botao_sortear)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var inputTextMaiorNumero = findViewById<TextView>(R.id.inputText_MaiorNumero)
        inputTextMaiorNumero = findViewById(R.id.inputText_MaiorNumero)
        configurarTextWatcher() // Chame a função aqui
    }

    fun sortear(view: View) {

        // Instanciei um objetdo do tipo "View"
        val maiorNumeroView = findViewById<TextView>(R.id.inputText_MaiorNumero)

        if (maiorNumeroView.text.isEmpty()) {
            Toast.makeText(this, "Por favor, insira o maior número!", Toast.LENGTH_SHORT).show()
            return
        } else {

            // Recupera o "maior número" a sortear
            val maiorNumero = maiorNumeroView.text.toString().toInt()

            if (numerosSorteados.size == maiorNumero) {

                Toast.makeText(
                    this,
                    "Não há mais números a sortear !",
                    Toast.LENGTH_SHORT
                ).show()
                return

            } else {

                var numero = getRandomico(maiorNumero + 1)
                while (numerosSorteados.contains(numero)) {
                    numero = getRandomico(maiorNumero + 1)
                }

                // Adiciona à lista de números sorteados
                numerosSorteados.add(numero)

                // Exibir lista de números já sorteados
                //val listaNumerosSorteadosView = findViewById<TextView>(R.id.editTextTextMultiLine_Sorteados)
                //listaNumerosSorteadosView.add (numero.toString())

                // instanciei um objeto do tipo TextView o "textNumeroSorteado" é o id do textView
                // Exibir número gerado
                val textNumeroSorteado = findViewById<TextView>(R.id.text_NumeroSorteado)
                textNumeroSorteado.setText(numero.toString(), TextView.BufferType.NORMAL)
            }
        }
    }

    fun getRandomico(maiorNumero: Int): Int {

        //  Random.nextInt -> gera um número "aleatório", entre from(inclusivo) e to(exclusivo)
        //  val numero = Random.nextInt(1, maiorNumero.text.toString().toInt() + 1)
        return Random.nextInt(1, maiorNumero) // Random.nextInt(1, maiorNumero + 1)
    }

    fun sair (view: View) {
        finish()
    }

    fun reiniciarLista() {
        numerosSorteados.clear()
    }

    fun configurarTextWatcher() {

        val inputTextMaiorNumero = findViewById<TextView>(R.id.inputText_MaiorNumero)

        inputTextMaiorNumero.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                reiniciarLista()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // ...
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // ...
            }
        })
    }
}


// =======================================================================================