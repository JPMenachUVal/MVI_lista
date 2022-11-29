package com.example.mvi_lista.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvi_lista.R
import com.example.mvi_lista.api.CartoonService
import com.example.mvi_lista.model.Cartoon
import kotlinx.android.synthetic.main.cartoon_item.view.*

/* 1) Hereda de la clase adapter de un RecyclerView OJO
 * 2) Es obligatorio el patrón View Holder
 * 3) La clase interna ViewHolder debe heredar(extender) de la clase abstracta View Holder
 * 4) Los métodos a implementar refieren mucho al tratamiento con el View Holder
* */
class CartoonListAdapter(private val cartoons: List<Cartoon>):
RecyclerView.Adapter<CartoonListAdapter.DataViewHolder>(){

    //Patrón ViewHolder para el adapter
    // necesitas una referencia de la View o Layout a mapear
    // Es necesario heredar de ViewHolder
    class DataViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView) {
        fun bind(cartoon: Cartoon) {
            itemView.txtName.text = cartoon.name
            itemView.txtLocation.text = cartoon.lastLocation
            val url = CartoonService.BASE_URL + cartoon.image
            Glide.with(itemView.context)
                .load(url)
                .into(itemView.ivCartoon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cartoon_item, parent
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(cartoons[position])
    }

    override fun getItemCount(): Int = cartoons.size

}