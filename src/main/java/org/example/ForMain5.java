/*
 * Copyright (c) 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ForMain5 {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Simone", "Bordet"),
                new Person("Alfonso", "Carlone")
        );

        // STEP1: Wrap the collection you want to iterate on.
        // It must make the current element available.
        Stream<Person> stream = Stream.of(people);

        // STEP10: inline the transformations and the accumulation.
        // The resulting code is now declarative, SQL style.
        List<String> result = stream
                .transform(person -> fullName(person))
                .transform(input -> new StringBuilder(input).reverse().toString())
                .accumulate(new ArrayList<>(), (list, item) -> {
                    list.add(item);
                    return list;
                });

        System.err.println("result = " + result);
    }

    private static String fullName(Person person) {
        return "%s %s".formatted(person.firstName(), person.lastName());
    }

    public record Person(String firstName, String lastName) {
    }

    public interface Stream<IN> {
        public static <T> Stream<T> of(Collection<? super T> collection) {
            // TODO
            return null;
        }

        // STEP4: The parameter should be an object that gets called by the implementation,
        // which will pass the current item and get back the transformed item.
        // Therefore, the design of Function below is trivial, and it is the parameter
        // of the transform method here.

        <OUT> Stream<OUT> transform(Function<IN, OUT> fn);

        // STEP9: What's needed is: the accumulator, and a BiFunction
        <ACC> ACC accumulate(ACC container, BiFunction<ACC, IN, ACC> accumulator);
    }

    // STEP5: Design the Function interface.
    interface Function<I, O> {
        O apply(I input);
    }

    // STEP8: Design the accumulation interface.
    // It is a BiFunction to accept the container and current element.
    interface BiFunction<I1, I2, O> {
        O apply(I1 input1, I2 input2);
    }
}
