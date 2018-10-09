# We'll display some more statistics about the processed code.

# Dagger generates classes based on your annotations. By default DexGuard already
# keeps the generated classes e.g. **$$ModuleAdapter
#
# You do however still need to manually preserve the corresponding base classes
# from your project. For example, for a generated class like
# com.example.SomeClass$$ModuleAdapter, you still need to specify:
# 
# -keep class com.example.SomeClass
# 
# That way, Dagger can combine the corresponding pairs of classes, based on
# their names. You can figure out the base classes by listing the generated
# classes in the gen directory (build/intermediates/classes) of your project
# (e.g. com/examples/SomeClass$$ModuleAdapter.java).
##---------------------------------------------------------------------------
#-optimizationpasses 1
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
##---------------------------------------------------------------------------

#-dontshrink
#-dontoptimize
#-dontobfuscate
-printmapping mapping.txt

##---------------------------------------------------------------------------
# The -optimizations option disables some arithmetic simplifications that Dalvik 1.0 and 1.5 can't handle.
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepattributes *Annotation*
#-keepattributes Signature
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-keepattributes Exceptions, InnerClasses
-keepattributes EnclosingMethod

##---------------------------------------------------------------------------
# ENUMERATION
-keep class **.R
-keep class **.R$* {
    <fields>;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}
-adaptresourcefilecontents !**.so

#-keepclasseswithmembernames,includedescriptorclasses class * {
#    native <methods>;
#}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
 }

 -keep class cbsa.consumer.kiosk.app.enumeration.Enums$** {
    **[] $VALUES;
    public *;
    *;
 }

 -keep class **$Properties

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference

-keep public class * extends android.support.design.widget.CoordinatorLayout.Behavior { *; }
-keep public class * extends android.support.design.widget.ViewOffsetBehavior { *; }

##---------------------------------------------------------------------------
## APACHE HTTP
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class org.apache.log4j** { *; }

-dontwarn com.google.appengine.**
-dontwarn org.apache.http.**
-dontwarn org.apache.log4j.**
-dontwarn com.kofax.kmc.**
-dontwarn com.kofax.**
-dontwarn dexguard.**
-dontwarn dmt.achilles.httpclient.**
-dontwarn cbsa.businesscontroller.origination.flow.fides.**
-dontwarn cbsa.businesscontroller.core.model.transaction.**
-dontwarn cbsa.common.utils.hockeyapp.**
-dontwarn com.amb.camerascanner.camera.**
-dontwarn android.webkit.**
##---------------------------------------------------------------------------
# RETROFIT 2.x
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
##---------------------------------------------------------------------------
## GSON
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-dontwarn com.google.gson.**

-keep class sun.misc.Unsafe { *; }

##---------------------------------------------------------------------------
## OKHTTP 3
-keep class okhttp3.internal.** { *; }
-keep interface okhttp3.internal.** { *; }
-dontwarn okhttp3.internal.**


-keep class android.net.http.** { *; }

##---------------------------------------------------------------------------
# BUTTER KNIFE 8.x
-keep public class * implements butterknife.Unbinder { public <init>(...); }

-dontwarn butterknife.internal.**
-keep class butterknife.** { *; }
-keep class butterknife.**$Finder { *; }
-keep class **$$ViewBinder { *; }
-keep class **$ViewHolder { *; }

-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

##---------------------------------------------------------------------------
## CRASHLYTICS
-keep class com.crashlytics.** { *; }

##---------------------------------------------------------------------------
# OKIO - A modern I/O API for Java
-dontwarn okio.**

##---------------------------------------------------------------------------
# RXJAVA
-dontwarn rx.**
-keep class rx.internal.util.unsafe.** { *; }

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}

##---------------------------------------------------------------------------
# RETRO LAMBDA
-dontwarn java.lang.invoke.*

##---------------------------------------------------------------------------
# HOCKEYAPP
-keepclassmembers class * {
  public void onClickUpdate(android.view.View);
}

-keepclassmembers class net.hockeyapp.android.UpdateFragment {
  *;
}

##---------------------------------------------------------------------------
## keep resouese filename
# enable this option to avoid Error: build release with obfuscation option.
# [INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING]
#-keepresourcefiles res/**

##---------------------------------------------------------------------------
# Material dialog
-keep class com.afollestad.materialdialogs.** { *; }

##---------------------------------------------------------------------------
# TOOLTIPVIEW
-keep class com.venmo.view.** { *; }

##---------------------------------------------------------------------------
-keep class cbsa.ui.widget.** { *; }

-keep class cbsa.ui.widget.Indicator.** { *; }

-keepclasseswithmembers class * {
    @cbsa.ui.widget.Indicator.* <fields>;
}
-keepclasseswithmembers class * {
    @cbsa.ui.widget.Indicator.* <methods>;
}

-keep class uk.co.chrisjenx.calligraphy.** { *; }
-keep class io.fabric.sdk.android.** { *; }
##---------------------------------------------------------------------------
##---------------------------------------------------------------------------
# KOFAX
-keep class com.kofax.** { *; }
-keep enum com.kofax.** { *; }
-keep interface com.kofax.** { *; }
-keep class io.card.** { *; }

-keepnames class com.kofax.android.**
-keepnames class com.kofax.kmc.ken.**
-keepnames class com.kofax.mobile.**

-dontwarn java.nio.**
-dontwarn org.codehaus.mojo.**
-dontwarn java.lang.reflect.**
-dontwarn java.lang.invoke.**
-dontwarn com.kofax.**
-dontwarn io.card.**
##---------------------------------------------------------------------------
# FIDES
-keep class com.futronictech.**  { *; }
##---------------------------------------------------------------------------
# PARCEL
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }
##---------------------------------------------------------------------------
-keep class com.amb.camerascanner.**  { *; }

-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*
-dontwarn javax.annotation.Nullable

## dagger
-dontwarn com.google.errorprone.annotations.*

## Fix missing dependencies by ignore it (by remove old lib scanner)
-dontwarn cbsa.scanner.enumeration.DocumentType

# support v4 view
-keep class android.support.v4.view.ViewPager { *; }

# MPAndroidChart
-keep class com.github.mikephil.charting.** { *; }
-keep class cbsa.consumer.kiosk.feature.widget.chart.** { *; }