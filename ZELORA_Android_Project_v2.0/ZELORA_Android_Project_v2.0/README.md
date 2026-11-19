# ZELORA Pro v2.0 — offline Android billing source

This release builds on v1.1 and retains the **zelora_billing_v1** localStorage key. Back up the installed app before updating. Keep the same Android package and signing key to preserve app data.

## Features
SKU/barcode/name suggestions, scanner keyboard input, experimental camera scan where BarcodeDetector and camera access are supported, held bills, split payments, customer credit limits and collections, returns with quantity checks, stock adjustments and movements, audit log, local admin/cashier PIN roles, reports, and print layout selector (80mm/58mm/A4). Android system print can print or save PDF. Product image URL can be stored, but no offline image caching is guaranteed.

## Build
Open the project in Android Studio; sync Gradle and select Build > Build APK(s). No compiled APK is included.

## Important limitations
- Camera scanning is **experimental** and may not work in file:// Android WebView due to secure-origin restrictions. External USB/Bluetooth scanners in keyboard mode are the dependable option.
- Printing depends on installed Android print services and printer drivers; direct ESC/POS Bluetooth printing is not implemented.
- PIN hashes are lightweight, not cryptographically secure, and localStorage is not encrypted. Not suitable for high-security or multi-device environments.
- Local stock updates are immediate within this installation only, not synchronized across devices.
- Credit allocations, returns and GST summaries are basic bookkeeping; verify GST/tax compliance separately.
- PDF sharing uses Android system print -> Save as PDF and the Android share sheet.
- Export JSON backups regularly. LocalStorage may be erased if app is uninstalled or its data cleared.
