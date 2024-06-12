package com.example.p3practico_cs21004.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.p3practico_cs21004.R
import com.example.p3practico_cs21004.network.Inmueble


class InmueblesAdapter : ListAdapter<Inmueble, InmueblesAdapter.InmuebleViewHolder>(InmuebleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InmuebleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_inmueble, parent, false)
        return InmuebleViewHolder(view)
    }

    override fun onBindViewHolder(holder: InmuebleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class InmuebleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val departamento: TextView = itemView.findViewById(R.id.txtDepartamento)
        private val municipio: TextView = itemView.findViewById(R.id.txtMunicipio)
        private val residencia: TextView = itemView.findViewById(R.id.txtResidencia)
        private val calle: TextView = itemView.findViewById(R.id.txtCalle)
        private val poligono: TextView = itemView.findViewById(R.id.txtPoligono)
        private val numeroCasa: TextView = itemView.findViewById(R.id.txtNumeroCasa)
        private val idPropietario: TextView = itemView.findViewById(R.id.txtIdPropietario)

        fun bind(inmueble: Inmueble) {
            departamento.text = inmueble.departamento
            municipio.text = inmueble.municipio
            residencia.text = inmueble.residencia
            calle.text = inmueble.calle
            poligono.text = inmueble.poligono
            numeroCasa.text = inmueble.numero_casa.toString()
            idPropietario.text = inmueble.id_propietario.toString()
        }
    }

    class InmuebleDiffCallback : DiffUtil.ItemCallback<Inmueble>() {
        override fun areItemsTheSame(oldItem: Inmueble, newItem: Inmueble): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Inmueble, newItem: Inmueble): Boolean {
            return oldItem == newItem
        }
    }
}