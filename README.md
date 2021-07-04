This repository contains my implementation for the _Gilded Rose Refactoring Kata_.
The source from which I started can be found [here](https://github.com/emilybache/GildedRose-Refactoring-Kata).

# Process
- Implement my own tests that validate the boundaries of the original kata.
These tests are based on the original logic and should match the existing boundaries.
    - I did lose some time here because I got stuck in wanting to generify the tests.
    At a certain point I simplified this and implemented the boundary tests as parameterized tests.
- Simplify the existing logic whilst maintaining test integrity.
- Step by step extract the existing code and migrate to a new architecture.
    - This reminds me of a [talk by Victor Rentea](https://www.youtube.com/watch?v=wY_CUkU1zfw)
    - I didn't do this entirely TDD, but build the new architecture and for the first implementation I implemented the logic first and then the tests, later implementations I implemented the tests first, then the logic, and then corrected/expanded the tests where necessary.
- Implement the newly requested feature: support for conjured items.

# Not implemented
- Min/Max validation on the input for the exercise, so currently it is possible to register items which have have more or less than 0 or 50 quality.
- No exclusions for conjured items, even though it might seem unlikely that it would apply to items which actually increase in quality.
- No combination of aging strategies or aging factors, as that is left unspecified in the boundary conditions
