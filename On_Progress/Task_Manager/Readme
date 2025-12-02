# 📌 Task Manager App

A simple, clean, and modern **Android Task Management Application** built using **Kotlin**, **MVVM Architecture**, **Room Database**, and **Material Design components**.

Users can **add, edit, star, complete, and organize tasks** easily with a smooth UI powered by ViewPager2 and Fragment-based navigation.

---

## 🚀 Features

### ✅ Task Management

- Add new tasks with title & description
- Edit existing tasks via bottom-sheet dialog
- Delete tasks
- Mark tasks as **starred / favorite**
- Tasks auto-update using LiveData + Room Flow

### ⭐ Task Filtering

The Home page includes **three tabs**:

- **My Tasks** → All tasks
- **Starred** → Only starred tasks
- **Completed** → Completed tasks

### 🧱 Robust Architecture

- **MVVM pattern**
- **Repository layer**
- **Room (Flow + LiveData)**
- **Fragments + ViewPager2 + TabLayout**
- **RecyclerView with custom adapter**

---

## 🛠️ Tech Stack

| Layer | Tools Used |
| --- | --- |
| **Language** | Kotlin |
| **Architecture** | MVVM |
| **Database** | Room |
| **UI Components** | ViewPager2, TabLayout, RecyclerView, BottomSheetDialog |
| **Coroutines** | ViewModelScope + Dispatchers.IO |
| **Dependency Injection** | ViewModelProvider.Factory |

---

## 📂 Project Structure

```
com.example.task_manager
│
├── data
│   ├── Task.kt
│   ├── TaskDAO.kt
│   ├── TaskDatabase.kt
│   └── TaskRepository.kt
│
├── ui
│   ├── main
│   │   ├── MainActivity.kt
│   │   ├── HomePagerAdapter.kt
│   │   ├── TasksFragment.kt
│   │   ├── AllTasksFragment.kt
│   │   ├── StarredTasksFragment.kt
│   │   └── CompletedTasksFragment.kt
│   │
│   ├── list
│   │   ├── TaskAdapter.kt
│   │   └── TaskViewHolder.kt
│   │
│   ├── addedit
│   │   └── AddEditTaskDialog.kt (if exists)
│   │
│   ├── utils
│   │   └── Extensions.kt
│   │
│   └── viewmodel
│       └── TaskViewModel.kt
│
└── resources (XML layouts, drawables, etc.)

```

---

## 🧩 How It Works

### ⭐ Room Database

- `Task` Entity
- `TaskDAO` for CRUD
- `TaskDatabase` for instance creation
- `TaskRepository` abstracts data operations

### ⭐ ViewModel

`TaskViewModel` exposes:

- `tasks: LiveData<List<Task>>` (from Room Flow)
- `addTask`, `updateTask`, `deleteTask`

### ⭐ UI Layer

- `MainActivity` hosts ViewPager2 + Tabs
- Each tab loads its own fragment with filtered tasks
- RecyclerView + TaskAdapter display tasks

---

## 📱 Screens & UX Flow

1. **Main Screen** → Lists all tasks
2. **Starred Tab** → Only favorite tasks
3. **Completed Tab** → Only finished tasks
4. **FAB Button** → Open BottomSheet to add a new task
5. **Tap Task** → Opens edit dialog
6. **Star Button** → Toggle starred

---

## 📦 Installation & Setup

1. Clone repo:

```bash
git clone https://github.com/yourusername/TaskManager.git

```

1. Open in **Android Studio**
2. Sync Gradle
3. Run on emulator / device

---

## 🧮 Room Database Migration Note

During development, the DB is created with:

```kotlin
fallbackToDestructiveMigration()

```

This means **Room resets the DB automatically** on schema changes (avoids crashes).

For production, create proper migrations.

---

## 🧑‍💻 Future Improvements

✔ Swipe to delete

✔ Mark task completed via checkbox

✔ Dark mode

✔ Notifications & deadlines

✔ Search functionality
