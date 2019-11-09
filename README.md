Write a program that will determine the type of a triangle. 
It should take the lengths of the triangle's three sides as input, and return whether the triangle is equilateral, isosceles or scalene.

The project consists of Java (Spring) backend and React frontend.

To create Spring Boot Jar, run
#### `mvn package`

To deploy Java side, simply run created jar.

To deploy frontend in dev mode, run
#### `npm start`

## Discussion

Spring Boot was chosen because of its ability to quickly provide MVP without configuration headaches.

Even though Qualifier and Validator both could be plain util-like classes, I decided to have them as Spring beans so that Spring manages their lifecycles and I am able to easily test them.
Another reason is that eventually there could be some stateful logic in the future that would require these classes to be declared as beans.

A separate DTO class is used to keep the Triangle class immutable.

Also, it would be nice to have Docker Compose configured for this project so that there are no difficulties with the start and there are no hardcoded values.

The Frontend part is very straightforward with a little touch of Tradeshift UI.
