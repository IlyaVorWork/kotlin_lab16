package com.ilyavorontsov.lab16

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class StoryFragment : Fragment() {
    private var picture: Int? = null
    private var text: Int? = null
    private var backBtnText: Int? = null
    private var contBtnText: Int? = null
    private var nextLink: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("arguments", arguments.toString())
        arguments?.let {
            picture = it.getInt("picture")
            text = it.getInt("text")
            backBtnText = it.getInt("backBtnText")
            contBtnText = it.getInt("contBtnText")
            nextLink = it.getInt("nextLink")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val ivPicture = view.findViewById<ImageView>(R.id.ivPicture)
        ivPicture.setImageResource(picture!!)

        val tvText = view.findViewById<TextView>(R.id.tvText)
        tvText.text = resources.getString(text!!)

        val btnBack = view.findViewById<Button>(R.id.btnBack)
        btnBack.text = resources.getString(backBtnText!!)
        btnBack.setOnClickListener {
            val pendingIntent = NavDeepLinkBuilder(view.context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.badEndingFragment)
                .createPendingIntent()
            pendingIntent.send()
        }

        val btnCont = view.findViewById<Button>(R.id.btnContinue)
        btnCont.text = resources.getString(contBtnText!!)
        btnCont.setOnClickListener {
            navController.navigate(nextLink!!)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(picture: String, text: String, backBtnText: String, contBtnText: String, nextLink: String) =
            StoryFragment().apply {
                arguments = Bundle().apply {
                    putString("picture", picture)
                    putString("text", text)
                    putString("backBtnText", backBtnText)
                    putString("contBtnText", contBtnText)
                    putString("nextLink", nextLink)
                }
            }
    }
}