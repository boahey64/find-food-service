# find-food-service
    
## build and run

### build
    ./mvnw clean verify    

### run
    ./mvnw spring-boot:run    

## rest API
### search
    /api/food/items?query={searchQuery}
    example:
    http://localhost:8080/api/food/items?query=broth
# get item    
    /api/food/items/{itemId}
    example:
    http://localhost:8080/api/food/items/6993
