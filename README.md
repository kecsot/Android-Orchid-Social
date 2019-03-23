## Android-Orchidea

This is an unfinished project where I tried a lot of library. It's my first Kotlin project so it was a great lesson for me but I'm too lazy to finish it because I want to try new architectures.

## Libs

-RxJava -Dagger2 -Retrofit -ShimmerLayout -EventBus

## What I failed

I created DTOs to communicate with the server. But I used it in the presenter and in the Views. Maybe next time better if I create ViewModels from the DTOs because if the server's DTO change than It will be hard ... Next time it will be better!

## Pagination

I made a Lumen WebService to this project. In the PostFragment and in the CommentFragment you can find a list. This list is load new items when you scrolling down. With the Lumen's default pagination I wrote an Abstract Layer to make it easy, when I need a list with pagination. So, just scroll and it will happens! :)
```
{
    "current_page":1,
    "data":[],
    "from":1,
    "last_page":4,
    "next_page_url":"...getPosts?page=2",
    "per_page":15,
    "prev_page_url":null,
    "to":15,
    "total":48
}
```
