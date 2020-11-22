# Github Trending
Implemention android architecture with 5 layers, This app also follows the mvvm template with tests for each layer.



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
        - State

- UseCases / Features can be found in the interactors folder in the domain package
- Each package (which represents an abstraction/layer in the clean architecture model) has a
build.gradle file that imports the project level dependencies.gradle file
- You can manage versions in the project level dependencies.gradle file
- When using GSON, you don't need the SerializedName annotation if the variable name in the
Java/Kotlin class matches the name of the property in the JSON object
