# Recommendations


### Bugs

* Duplicate user while sign-up user should give 409 (conflict) but it is giving 422
* By giving invalid Http method, it gives an HTML response , instead should give 405 methog not allowed.

### Improvements

* Token from sign up url is not used in login api for the authenticating.
* Login api also gives a token. And there is not validation.
* Sign Up api should give valid response detials of the user registered.