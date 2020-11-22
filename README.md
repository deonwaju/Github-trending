# Github Trending
Android Clean Architecture tutorial on Caster.io

## Ending TODO
- [x] Look over, understand, and format the activities and layouts in the mobile-ui module
- [x] Fix the bugs and dependency injection issues
- [x] Build the mobile-ui module successfully
- [x] Move the instrumentation tests over to this project
- [x] Test project
- [x] Debug project to work on phone
- [x] Test on phone
- [ ] Clean up gradle files
- [ ] Do a general code clean up (remove warnings as much as possible)
- [ ] Fix UI things
- [ ] Experiment
- [ ] Done! Harness the power of Android dev to create more apps :D

## Notes
- Layers
    - Data 
        - Model
        - Repository
    - Domain
        - Model
        - UseCase
    - Presentation
        - Model
        - ViewModel
        - View
- UseCases / Features can be found in the interactors folder in the domain package
- Each package (which represents an abstraction/layer in the clean architecture model) has a
build.gradle file that imports the project level dependencies.gradle file
- You can manage versions in the project level dependencies.gradle file
- When using GSON, you don't need the SerializedName annotation if the variable name in the
Java/Kotlin class matches the name of the property in the JSON object