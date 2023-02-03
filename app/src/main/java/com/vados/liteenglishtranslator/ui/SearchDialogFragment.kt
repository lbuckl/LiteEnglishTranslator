package com.vados.liteenglishtranslator.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vados.liteenglishtranslator.databinding.SearchDialogFragmentBinding

/**
 * Фргагмент для ввода слова пепеводимого с русского на наглийский
 */
class SearchDialogFragment : BottomSheetDialogFragment() {

    private var _binding: SearchDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null

    private val textWatcher = object : TextWatcher {

        /**
         * Действие при изменении текста
         */
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            setElementVision()
        }

        /**
         * Действие перед изменением текста
         */
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //Nothing TODO
        }

        /**
         * Действие ппосле изменением текста
         */
        override fun afterTextChanged(s: Editable) {
            //Nothing TODO
        }
    }

    /**
     * Функция устанавливает видимость элементов при вводе/удалении текста
     */
    private fun setElementVision(){
        if (binding.searchEditText.text != null &&
            binding.searchEditText.text.toString().isNotEmpty()
        ) {
            binding.searchButtonTextview.visibility = View.VISIBLE
            binding.clearTextImageview.visibility = View.VISIBLE
        } else {
            binding.searchButtonTextview.visibility = View.GONE
            binding.clearTextImageview.visibility = View.GONE
        }
    }

    /**
     * Функция кликкер для начала переода слова
     */
    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButtonTextview.setOnClickListener(onSearchButtonClickListener)
        binding.searchEditText.addTextChangedListener(textWatcher)

        addOnClearClickListener()

        setElementVision()
    }

    override fun onDestroyView() {
        _binding = null
        onSearchClickListener = null
        super.onDestroyView()
    }

    /**
     * Функция кнопки "удалить введённые данные"
     */
    private fun addOnClearClickListener() {
        binding.clearTextImageview.setOnClickListener {
            binding.searchEditText.setText("")
        }
    }

    /**
     * Интервейс кликкера для начала перевода слова
     */
    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }
}
