package com.comunidadedevspace.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_RESULT_IMC = "ResultActivity.KEY_IMC"

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val btnTabela = findViewById<Button>(R.id.btn_tabela)
        btnTabela.setOnClickListener {
            val intent = Intent(this, TableActivity::class.java)
            startActivity(intent)
        }

        val btnRecalcular = findViewById<Button>(R.id.btn_recalcular)
        btnRecalcular.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val result = intent.getFloatExtra(KEY_RESULT_IMC, 0f)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvClassificacao = findViewById<TextView>(R.id.tv_classificacao)
        val tvInstrucao = findViewById<TextView>(R.id.tv_instrucao)

        tvResult.text = result.toString()

        val (classificacao, instrucao) = when {
            result <= 18.5f -> "ABAIXO DO PESO" to "Você está abaixo do peso. Considere procurar um especialista em saúde ou nutricionista para ganhar peso de forma saudável."
            result <= 24.9f -> "PESO IDEAL" to "Você está com o peso ideal. Continue com seus bons hábitos alimentares e pratique atividades físicas regularmente!"
            result <= 29.9f -> "SOBREPESO" to "Você está com sobrepeso. Pode ser interessante buscar orientação de um nutricionista e incluir exercícios na sua rotina."
            result <= 34.9f -> "OBESIDADE" to "Você está na faixa de obesidade. Consulte um profissional de saúde para montar um plano seguro de perda de peso."
            result <= 39.9f -> "OBESIDADE SEVERA" to "Você está na faixa de obesidade severa. É recomendado buscar ajuda médica e nutricional para reduzir riscos à saúde."
            else -> "OBESIDADE MÓRBIDA" to "Você está na faixa de obesidade mórbida. Procure orientação médica o quanto antes para garantir seu bem-estar e evitar complicações."
        }
        tvInstrucao.text = instrucao

        tvClassificacao.text = classificacao

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}