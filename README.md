# SubscriptionTracker

A beginner-friendly Java console app to keep track of subscriptions, costs, and renewal dates.

## Features
- View a list of subscriptions
- Add a subscription with its cost, billing cycle, and next renewal date
- See renewals due within the next 30 days
- Estimate total monthly subscription spending
- Remove a subscription
- Includes sample subscriptions so you can try it immediately

## Requirements
- Java 8 or newer

## Run the app

1. Install Java if it is not already installed.
2. Open a terminal in the project folder.
3. Compile the source files:

   ```bash
   javac src/Subscription.java src/Main.java
   ```

4. Run the app:

   ```bash
   java -cp src Main
   ```

## Date format
Enter renewal dates in `YYYY-MM-DD` format, for example `2026-12-01`.

## Java concepts used
- Classes and objects
- Variables and data types
- Methods
- `ArrayList`
- Loops and `if` statements
- `Scanner` for user input
- Exception handling
- `LocalDate` for renewal dates

## Note
This starter version stores subscriptions in memory. Any subscriptions you add are cleared when you exit the program.
