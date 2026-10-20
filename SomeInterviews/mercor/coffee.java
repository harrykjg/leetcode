package SomeInterviews.mercor;

public class coffee {
    /*
    Probability based question.

There are two coffee shops. Coffee shop A and coffee shop B that are equally good. You buy two drinks from A and two drinks from B. Each drink gets a quality score and all four of these scores are random and independent.

What is the probability that both drinks from A are strictly better than both drinks from B? Assume ties never happen.

Answer

We're looking for the combinations were both of A scores are strictly better than both of B scores.


A2 > A1 or A1 > A2 and B1 and B2 can be in any order.

That's 2 x 2 = 4

there are 4! = 24 possibilites

so... 4/24 = 1/6

另一个表述
given 4 distinct coffees, 2 from coffee shop A and 2 from shop B where scores are random and fair, what is the chance that both coffees from A are better than both coffees from B
(2x1x2x1) / (4!) - 2 ways to arrange first A, then 1 remaining A, 2 ways to arrange B, then 1 remaining B so Ax2 > Ax1 > Bx2 > Bx1 / 4P4 or 4! ways to arrange all coffees
     */
}
