package ua.turskyi.visitedcountries.features.allcountries.view.adapter

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import ua.turskyi.domain.model.Country
import ua.turskyi.visitedcountries.R

class AllCountriesAdapter :
    PagedListAdapter<Country, AllCountriesAdapter.ViewHolder>(COUNTRIES_DIFF_CALLBACK) {

    var onCountryClickListener: ((country: Country) -> Unit)? = null

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Country]
     * has been updated.
     */
    companion object {
        val COUNTRIES_DIFF_CALLBACK: DiffUtil.ItemCallback<Country> =
            object : DiffUtil.ItemCallback<Country>() {
                override fun areItemsTheSame(
                    oldItem: Country,
                    newItem: Country
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: Country,
                    newItem: Country
                ): Boolean {
                    return oldItem.name == newItem.name && oldItem.visited == newItem.visited
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.list_item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCountry = getItem(position) as Country
        holder.tvCountry.text = currentCountry.name
        showPicturesInSVG(currentCountry, holder)
    }

    private fun showPicturesInSVG(
        country: Country,
        holder: ViewHolder
    ) {
        val uri: Uri = Uri.parse(country.flag)

        GlideToVectorYou
            .init()
            .with(holder.itemView.context)
            .withListener(object : GlideToVectorYouListener {
                override fun onLoadFailed() {
                    showPicturesInWebView(holder, country)
                }

                override fun onResourceReady() {
                    holder.ivFlag.visibility = VISIBLE
                    holder.wvFlag.visibility = GONE
                }
            })
            .setPlaceHolder(R.drawable.anim_loading, R.drawable.ic_broken_image)
            .load(uri, holder.ivFlag)
    }

    private fun showPicturesInWebView(
        holder: ViewHolder,
        country: Country
    ) {
        holder.ivFlag.visibility = GONE
        holder.wvFlag.run {
            webViewClient = WebViewClient()
            visibility = VISIBLE
            setBackgroundColor(Color.TRANSPARENT)
            setInitialScale(8)
            loadUrl(country.flag)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountry: TextView = itemView.findViewById(R.id.tvCountry)
        val ivFlag: ImageView = itemView.findViewById(R.id.ivFlag)
        val wvFlag: WebView = itemView.findViewById(R.id.wvFlag)

        init {
            itemView.setOnClickListener {
                onCountryClickListener?.invoke(getItem(layoutPosition) as Country)
            }
        }
    }
}

