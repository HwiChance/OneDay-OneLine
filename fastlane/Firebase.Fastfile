# fastlane deploy_app_distribution
platform :android do
    desc "Submit a new Release Build to Firebase App Distribution"
    lane :publishDevDebug do
        gradle(task: "clean assembleRelease")

        firebase_app_distribution(
            service_credentials_file: "firebase_credentials.json",
            app: ENV["APP_ID"],
            release_notes: "Test version of devDebug build."
        )

        latest_release = firebase_app_distribution_get_latest_release(
            app: ENV["APP_ID"],
            service_credentials_file: "firebase_credentials.json"
        )
        increment_version_code({ version_code: latest_release[:buildVersion].to_i + 1 })
    end
end