package com.example.mvi_lista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvi_lista.api.CartoonService
import com.example.mvi_lista.view.CartoonListAdapter
import com.example.mvi_lista.view.MainState
import com.example.mvi_lista.viewmodel.MainViewModel
import com.example.mvi_lista.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var adapter = CartoonListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        //La vista recibe un estado luego de la interacción desde viewmodel
        //la vista tiene que en base a ese estado cambiar algo visual
        setupStatesToView()
    }

    private fun setupStatesToView() {
        lifecycleScope.launch {
            viewModel.state.collect{ collector ->
                when(collector) {
                    is MainState.Loading -> {
                        btnCartoons.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                        pbProgress.visibility = View.VISIBLE
                    }
                    is MainState.Cartoons -> {
                        btnCartoons.visibility = View.GONE
                        pbProgress.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }
                    else -> {
                        btnCartoons.visibility = View.VISIBLE
                        pbProgress.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setupUI() {
        //Configurar el Adapter
        //1) Configurar layout(contenedor) y distribución
        //2)Configurar adapter(adaptador) que usará el recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.run {
            //Personalización de aspecto, separación entre ítems
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        //Configurar el ViewModel
        initializeViewModel()
    }

    private fun initializeViewModel() {
        //Necesitar un orquestador o director para crear este objeto
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(CartoonService.api)
        ).get(MainViewModel::class.java)
    }
}