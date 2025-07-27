# 🛸 Yassir Rick & Morty Compose App

A clean and modern Rick and Morty character search app built using **Jetpack Compose**, **Ktor**, **Koin**, and the **MVI architectural pattern**.  
Developed as part of the **Yassir Android Engineer recruitment challenge**.

---

## 🎥 Demo

https://github.com/user-attachments/assets/8ee76281-6770-4d35-a2de-091a167b577f

---

## 🚀 Features

- 🔍 Debounced character search
- 💡 Jetpack Compose + Material 3 UI
- 🧠 MVI architecture with unidirectional data flow
- 🌐 Networking with Ktor client
- ⚙️ Dependency injection using Koin
- 🧱 Clean architecture (data, domain, presentation layers)
- 📱 Fully reactive UI with `StateFlow` and `ViewModel`

---

## 📦 Tech Stack

| Layer        | Technologies                             |
|--------------|------------------------------------------|
| UI           | Jetpack Compose, Material 3              |
| Architecture | MVI (Model-View-Intent)                  |
| State        | ViewModel, `StateFlow`, `Channel`        |
| DI           | Koin                                     |
| Network      | Ktor Client                              |
| Language     | Kotlin                                   |

---

## 🧩 Project Structure

The project follows clean architecture principles, organized as follows:

📦 app
┣ 📂 core
┃ ┣ 📂 domain
┃ ┣ 📂 data
┃ ┣ 📂 presentation
┃ ┣ 📂 navigation
┣ 📂 di # Dependency injection setup
┣ 📂 ui
┣  📂 rickmorty
┃ ┣ 📂 domain
┃ ┣ 📂 data
┃ ┗ 📂 presentation
┗ MainActivity.kt




---

## 🧪 Testing

Unit and UI tests are structured and modular (not included in this challenge submission but the architecture is test-friendly).

---

## 📄 License

This project is open-source and free to use under the MIT license.

