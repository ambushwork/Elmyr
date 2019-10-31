
Welcome to Elmyr's documentation!
=================================

Elmyr is a Kotlin library providing tools to forge “random” - and reproducible - values, specifically useful for tests.

The code is open source, and available on GitHub_.

Introduction
------------

Being an adept of testing code, I write a lot of tests. The problem is that in my tests, my fake / test data always look the same. My user names are always `Bob` and `Alice`, aged `42` or `69`, with userId `4816152342` or `24601`, and eating `“spam”`, `“eggs”` and `“bacon”`. 

The problem is, the more test I write, the less I'm confident in my fake values, because they're always the same. 

This is where Elmyr kicks in, allowing you to create fake data based on a few constraints, making your test data random, and yet reproducible. 


Why Elmyr
---------

The concept of this library is to be able to generate values that could pass for actual production values, but are not. Which is a kind of forgery. This library is named after `Elmyr de Hory`__, a XXth century art forger


.. _GitHub: https://github.com/xgouchet/Elmyr
.. __ : https://en.wikipedia.org/wiki/Elmyr_de_Hory

.. toctree::
   :maxdepth: 1
   :caption: Contents:
   :glob:

   getting_started
   faq
