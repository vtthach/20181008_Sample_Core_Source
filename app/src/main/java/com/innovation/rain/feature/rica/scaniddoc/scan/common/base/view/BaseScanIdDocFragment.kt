package com.innovation.rain.feature.rica.scaniddoc.scan.common.base.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.common.base.presenter.BaseScanIdDocPresenter
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.scan_id_fragment.*
import java.io.File
import javax.inject.Inject


open class BaseScanIdDocFragment<T : BaseScanIdDocPresenter> : BasePresenterInjectionFragment<T>(), BaseScanIdDocView {

    @Inject
    lateinit var mPresenter: T

    override fun getPresenter() = mPresenter

    override fun getLayoutId() = R.layout.scan_id_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btAccept.isActivated = true
        showTextureView(true)
        mPresenter.setTextureView(viewTexture)
        btCapture.setOnClickListener { mPresenter.capture() }
        btCancel.setOnClickListener { showTextureView(true) }
        btAccept.setOnClickListener { mPresenter.uploadImage() }
    }

    override fun backToHomeRica() {
        val intent = Intent().apply {
            putExtras(Bundle().apply {
                putInt(RicaHomeFragment.BUNDLE_KEY_RICA_STATE, RicaState.STATE_DONE.id)
            })
        }
        activity?.setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun reviewImage(file: File) {
        activity?.runOnUiThread {
            showTextureView(false)
            imagePreview.setImageURI(Uri.fromFile(file))
            imagePreview.rotation = 180f
        }
    }

    override fun showTextureView(isShow: Boolean) {
        val visibilityTextureView = if (isShow) View.VISIBLE else View.GONE
        val visibilityImageView = if (isShow) View.GONE else View.VISIBLE

        btCapture.visibility = visibilityTextureView
        viewTexture.visibility = visibilityTextureView

        imagePreview.visibility = visibilityImageView
        btAccept.visibility = visibilityImageView
        btCancel.visibility = visibilityImageView
    }
}
