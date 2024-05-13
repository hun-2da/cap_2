plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "codingadventure.community.myapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "codingadventure.community.myapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("jp.wasabeef:richeditor-android:2.0.0") //editer 추가
    implementation ("com.github.yukuku:ambilwarna:2.0.1")  // 팔레트

    implementation("org.jsoup:jsoup:1.17.2") // json 파싱용

    //----------------------------------------------------------------------- 파이어베이스 로그인용
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-auth")

    //------------------------------------------------------------------------ 파이어베이스 접근용

    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-database:20.3.1")

    //-------------------------------------------------------------------------- 캘린더 용
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.core:core-ktx:1.13.1")

    //--------------------------------------------------------------------------- 리싸이클러뷰 업데이트 (android x 전체 업데이트 할 시 필요없지만 코드 호환성을 위해 개별 업데이트)
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    //---------------------------------------------------------------------------

    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("com.github.shuhart:material-calendar:1.1.0")
    //implementation("com.prolificinteractive.materialcalendarview:material-calendarview:2.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}