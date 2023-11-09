import SwiftUI
import Firebase // import this

@main
struct iOSApp: App {
    // init with the FirebaseApp.configure()
    init(){
        FirebaseApp.configure()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
