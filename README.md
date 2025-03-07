# Harry Potter App

**Harry Potter App**, Harry Potter evrenindeki karakterlerin listesini ve detaylarını gösteren bir Android uygulamasıdır. Kullanıcılar, karakterlerin isimleri üzerinden arama yapabilir, detay sayfasına geçiş yaparak karakter hakkında daha fazla bilgi edinebilirler.

Uygulama, Jetpack Compose, Hilt ve Retrofit gibi modern Android teknolojileri ile geliştirilmiştir.

## Özellikler

- **Karakter Listesi:** Tüm Harry Potter karakterlerinin listesini gösterir.
- **Arama:** Karakterler arama fonksiyonu ile filtrelenebilir.
- **Karakter Detayları:** Her bir karakterin detaylı bilgilerine ulaşılabilir.
- **Hilt ile Dependency Injection:** Uygulama, Hilt kullanılarak daha verimli ve test edilebilir bir yapı ile geliştirilmiştir.
- **Retrofit ile API İletişimi:** Karakter verileri, REST API üzerinden alınır ve UI'de gösterilir.
- **Hata Yönetimi**: API'de bir sorun olması durumunda, kullanıcıya uygun hata mesajları gösterilir.

## Kullanılan Teknolojiler

- **[Kotlin](https://kotlinlang.org/):** Android uygulama geliştirme için kullanılan ana programlama dili.
  
- **[Jetpack Compose](https://developer.android.com/jetpack/compose):** Modern UI geliştirme kütüphanesi. Kullanıcı arayüzü tasarımında Compose kullanılmıştır.
- **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android):** Dependency Injection yönetimi için kullanılan kütüphane. Uygulama, Hilt ile modüler ve test edilebilir hale getirilmiştir.
- **[Retrofit](https://square.github.io/retrofit/):** API çağrıları yapmak için kullanılan kütüphane. Harry Potter karakterlerini almak için API ile iletişim kurar.
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel):** UI ile iş mantığını ayırmak için kullanılan mimari bileşen. Uygulama durumu burada yönetilir.
- **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** Asenkron işlemler için kullanılan Kotlin yapısı. Retrofit API çağrıları arka planda Coroutine ile yapılır.
- **[Navigation Component](https://developer.android.com/guide/navigation):** Kullanıcıyı ekranda yönlendirmek için kullanılan Android bileşeni. Farklı ekranlar arasında geçişler burada yapılır.
- **[Material Design Components](https://material.io/develop/android):** Android için UI bileşenleri sunan tasarım sistemidir. Uygulama arayüzü, Material Design standartlarına uygun olarak tasarlanmıştır.

## Preview

![Image](https://github.com/user-attachments/assets/3db95d84-1e62-4ce9-9f5c-ff91d32cae4b)


## Screenshots

| Açılış Ekranı| Karakter Listesi Ekranı | Arama Ekranı| Karakter Detay Ekranı |
|----------------|--------------|-----------------|-----------------------|
| ![Splash Screen](https://github.com/user-attachments/assets/1f12285e-dc81-4cbd-b01d-8b9ef38dd183) | ![Login Screen](https://github.com/user-attachments/assets/aecf5af0-9fa8-4511-8c46-b0f5f5451b2a) | ![News Headlines](https://github.com/user-attachments/assets/0ffb73ee-82c1-46e3-b617-1d38563fa5f6) | ![Character List Screen](https://github.com/user-attachments/assets/4f6e0b7b-e2ae-4fd3-969c-c8922b17747b) |

