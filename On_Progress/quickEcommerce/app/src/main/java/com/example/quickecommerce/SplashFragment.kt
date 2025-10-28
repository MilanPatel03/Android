package com.example.quickecommerce


/*
    What’s happening here?

        activity?.window?.apply { ... }

        activity? → means "use the Activity if it’s not null" (safe call).

        .window → gives us access to the app’s window (basically the area including status bar, navigation bar, etc.).

        .apply { ... } → lets us configure the window in a block.

     👉 In plain words: "If the activity exists, grab its window and customize it."

        val statusBarColors = ContextCompat.getColor(requireContext(), R.color.yellow)

        ContextCompat.getColor() is a helper that fetches a color safely across different Android versions.

        R.color.yellow is your color resource (defined in res/values/colors.xml).

     👉 This line just says: "Get my app’s yellow color and store it in a variable."

        statusBarColor = statusBarColors

        Sets the status bar background color (the top bar where time/battery are shown).

     👉 This makes your status bar yellow. 🌟

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { ... }

        Android keeps adding features.

        Build.VERSION.SDK_INT → current device’s Android version.

        VERSION_CODES.M → Android 6.0 (Marshmallow).

     👉 This block runs only on Marshmallow or higher.

        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        decorView = the top-level view of the window.

        SYSTEM_UI_FLAG_LIGHT_STATUS_BAR → tells Android:
        "Make the status bar icons (battery, clock, etc.) dark instead of white, so they’re visible on light backgrounds."

     👉 Without this, yellow background + white icons = hard to see.

     🧑‍💻 TL;DR in simple English

        This function:

        Finds your Activity’s window.

        Paints the status bar yellow.

        If Android ≥ 6.0, changes the status bar icons to dark so they’re visible.
    */


