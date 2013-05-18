A-Driver - Async wrapper for WebDriver
=============================================

What is it?
-----------

A-Driver is a convenient wrapper around Webdriver instances, that provides support for lazy
evaluation of WebElements, and implicit waiting for elements to become available/usable on
a page, instead of having to explicitly define WebDriverWait implementations in vanilla WebDriver.


Where did it come from?
-----------------------

Bored of having to declare WebDriverWaits throughout my code base, I looked for frameworks that handle this
implicitly. Having just recently read the excellent Growing Object Oriented Software, Guided by Tests 
(http://www.growing-object-oriented-software.com), I looked at the Window Licker tool that was used extensively
in their examples (and developed by Nat Pryce, co-author of the book).

Window Licker seemed to offer everything I needed, but had 2 rather significant problems. First, it didn't quite offer
everything I needed (support for collections of elements was the most notable omission). This in itself could be overcome
if it wasn't for problem number 2 - the tool no longer seemed to be supported, and hadn't been worked on in nearly 3 years.

Initially, I experimented with Window Licker, trying to add the features I needed, with a view to using this new version on my
own personal projects, but it became clear that starting again was the way forward. However, comparing A-Driver with Window Licker,
you will notice more than a couple of similarities.


How do I use it?
----------------

Info to come...


License
-------
Copyright 2013 stuforbes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.