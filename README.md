# Github Trending
Kotlin project used to fetch list of trending github projects with bookmark feature.
Implement android architecture with 5 layers, This app also follows the mvvm template with tests for each layer.



## Notes
- Layers
    - Domain
        - Model
        - UseCase
    
    - Data 
        - Model
        - Repository

    - Cache 
        - Database
        - model

    - Presentation
        - Model
        - ViewModel
        - View
        - State
    
    - Mobile-Ui
        - Ui

- UseCases / Features can be found in the interactors folder in the domain package
- Each package (which represents an abstraction/layer in the clean architecture model) has a
build.gradle file that imports the project level dependencies.gradle file
- You can manage versions in the project level dependencies.gradle file
- When using GSON, you don't need the SerializedName annotation if the variable name in the
Java/Kotlin class matches the name of the property in the JSON object
