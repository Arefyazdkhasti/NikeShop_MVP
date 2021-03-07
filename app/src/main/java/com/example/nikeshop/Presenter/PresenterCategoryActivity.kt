package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelCategoryActivity
import com.example.nikeshop.View.ViewCategoryActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.net.CountryPresenterListener
import kotlin.coroutines.coroutineContext

class PresenterCategoryActivity(
    private val view: ViewCategoryActivity,
    private val model: ModelCategoryActivity
) : BaseLifeCycle {


    override fun onCreate() {
        setTitle()
        setDataRecyclers()
        onClickHandler()
    }

    override fun onRefresh() {
        setDataRecyclers()
    }

    private fun setDataRecyclers() {
        view.showProgress()

        /*model.getDataNewProductsByID(object : CountryPresenterListener<List<DataProduct>> {
            override fun onResponse(data: List<DataProduct>) {
                view.setDataNewProducts(data)
            }

            override fun onFailure(error: String) {
                view.showToast(error)
            }

        })

        model.getDataTopSellingProductsByID(object :CountryPresenterListener<List<DataProduct>>{
            override fun onResponse(data: List<DataProduct>) {
                view.setDataTopSellingProducts(data)
            }

            override fun onFailure(error: String) {
                view.showToast(error)
            }

        })*/
        model.getDataCategory(
            object : CountryPresenterListener<List<DataProduct>> {
                override fun onResponse(data: List<DataProduct>) {
                    view.setDataRecycler(data)
                    view.hideProgress()
                }

                override fun onFailure(error: String) {
                    view.showToast(error)
                    view.hideProgress()
                }

            },view.sortBy)
    }


    private fun setTitle() {
        view.setTitle(model.getTitleAsIntent())

    }

    private fun onClickHandler() {
        view.onBackClicked()

    }


    override fun onDestroy() {}

}