package com.ilyavorontsov.lab16

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDeepLinkBuilder

class EndingFragment : Fragment() {
    private var picture: Int? = null
    private var text: Int? = null
    private var btnText: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            picture = it.getInt("picture")
            text = it.getInt("text")
            btnText = it.getInt("btnText")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivPicture = view.findViewById<ImageView>(R.id.ivPicture)
        ivPicture.setImageResource(picture!!)

        val tvText = view.findViewById<TextView>(R.id.tvText)
        tvText.text = resources.getString(text!!)

        val btnAgain = view.findViewById<Button>(R.id.btnAgain)
        btnAgain.text = resources.getString(btnText!!)
        btnAgain.setOnClickListener {
            val pendingIntent = NavDeepLinkBuilder(view.context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.introFragment)
                .createPendingIntent()
            pendingIntent.send()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(picture: String, text: String, btnText: String) =
            EndingFragment().apply {
                arguments = Bundle().apply {
                    putString("picture", picture)
                    putString("text", text)
                    putString("btnText", btnText)
                }
            }
    }
}