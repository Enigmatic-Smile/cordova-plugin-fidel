# Fidel Cordova plugin change log

## 1.3.0

- Update the *android-sdk* distribution to the [Enigmatic-Smile](https://github.com/Enigmatic-Smile) organization.

## 1.2.0

- Now the SDK allows you to select multiple allowed countries from which the user can pick. Please check the docs for information about the new `allowedCountries` option.
- Removed the `country` option. To set a default country and not allow the user to pick the country, set a single country in the new `allowedCountries` option. Check the example project or the README docs to see how to do that.
- If available, the linking result object now includes the `firstNumbers` field. So, if in the Fidel Dashboard, under your security settings, you allow showing the first numbers of the linked card numbers, the information will be available in the linking result object too. If you do not allow showing the first numbers in the linking result, the `firstNumbers` field will return `"******"` (just like the object which the Fidel API returns).
