# Custom Image List

Custom Image List is a demo project for a custom view called AssignmentView.

## AssignmentView

AssignmentView is a custom recycler view with some extra functionality.

Basically it is a list view that displays the images of which urls are given using
the public method: @setImageUrlList(imageUrlList: List<String>)

For each imageUrl, AssignmentView;
loads the image,
displays a placeholder image while loading,
logs the load time to logcat,
sends the load time to a remote server,
displays an error image if the url is broken or does not contain an image,
caches the image and displays it.

### Usage

``` kotlin

val assignmentView = view.findViewById<AssignmentView>(R.id.assignmentView)
assignmentView.layoutManager = LinearLayoutManager(requireContext()) // can be given in XML
val imageUrlList: List<String> = listOf("https://db62cod6cnasq.cloudfront.net/user-media/0/image1-1mb.png")
assignmentView.setImageUrlList(imageUrlList)

```

## Used dependencies

Glide for image loading and caching
Coroutines for async operations
Retrofit for network operations

## Applied Android best practices

Data Binding
Dependency Injection (through constructor manually)

## Demo Screen

Simple screen with MVVM architecture to show functionality of AssignmentView.
Contains an AssignmentView to show images and a "Shuffle" button which changes the order of images.

Note: Since images are full screen, shuffle effect is not easily noticeable.
That is why after every button click, first item of shuffled list is shown.

## Experimental AssignmentImageView

This is another custom view with its own viewModel and repository for displaying a single image.
