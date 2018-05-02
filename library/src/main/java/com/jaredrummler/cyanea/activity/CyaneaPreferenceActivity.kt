package com.jaredrummler.cyanea.activity

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.preference.PreferenceActivity
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatDelegate
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import com.jaredrummler.cyanea.Cyanea
import com.jaredrummler.cyanea.CyaneaResources
import com.jaredrummler.cyanea.delegate.BaseAppCompatDelegate
import com.jaredrummler.cyanea.delegate.CyaneaDelegate

abstract class CyaneaPreferenceActivity : PreferenceActivity(),
    BaseAppCompatDelegate, BaseCyaneaActivity {

  private val appCompatDelegate: AppCompatDelegate by lazy {
    AppCompatDelegate.create(this, null)
  }

  private val delegate: CyaneaDelegate by lazy {
    CyaneaDelegate.create(this, getCyanea(), getThemeResId())
  }

  private val resources: CyaneaResources by lazy {
    CyaneaResources(super.getResources(), getCyanea())
  }

  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(delegate.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    delegate.onCreate(savedInstanceState)
    appCompatDelegate.installViewFactory()
    appCompatDelegate.onCreate(savedInstanceState)
    super.onCreate(savedInstanceState)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    appCompatDelegate.onPostCreate(savedInstanceState)
    delegate.onPostCreate(savedInstanceState)
  }

  public override fun onStart() {
    super.onStart()
    delegate.onStart()
  }

  override fun onResume() {
    super.onResume()
    delegate.onResume()
  }

  override fun onPostResume() {
    super.onPostResume()
    appCompatDelegate.onPostResume()
  }

  override fun onStop() {
    super.onStop()
    appCompatDelegate.onStop()
  }

  override fun onDestroy() {
    super.onDestroy()
    appCompatDelegate.onDestroy()
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    appCompatDelegate.onConfigurationChanged(newConfig)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    delegate.onCreateOptionsMenu(menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun invalidateOptionsMenu() {
    appCompatDelegate.invalidateOptionsMenu()
  }

  override fun onTitleChanged(title: CharSequence, color: Int) {
    super.onTitleChanged(title, color)
    appCompatDelegate.setTitle(title)
  }

  override fun setContentView(@LayoutRes layoutResID: Int) {
    appCompatDelegate.setContentView(layoutResID)
  }

  override fun setContentView(view: View) {
    appCompatDelegate.setContentView(view)
  }

  override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
    appCompatDelegate.setContentView(view, params)
  }

  override fun addContentView(view: View, params: ViewGroup.LayoutParams) {
    appCompatDelegate.addContentView(view, params)
  }

  override fun getSupportActionBar(): ActionBar? = appCompatDelegate.supportActionBar

  override fun getMenuInflater(): MenuInflater = appCompatDelegate.menuInflater

  override fun getResources(): Resources = resources

  override fun getDelegate(): AppCompatDelegate = appCompatDelegate

  override fun getCyanea(): Cyanea = Cyanea.instance

  override fun getCyaneaDelegate(): CyaneaDelegate = delegate

  @StyleRes abstract override fun getThemeResId(): Int

}