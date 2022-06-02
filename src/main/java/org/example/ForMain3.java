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

import java.util.Collection;
import java.util.List;

public class ForMain3 {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Simone", "Bordet"),
                new Person("Alfonso", "Carlone")
        );

        // STEP1: Wrap the collection you want to iterate on.
        // It must make the current element available.
        Stream<Person> stream = Stream.of(people);

        // STEP2: Add a transform() method to perform the transformation.
        // STEP3: What signature should it have?
        Stream<String> stringStream = stream.transform(new Function<>() {
            @Override
            public String apply(Person person) {
                return fullName(person);
            }
        });

        // STEP6: In order to compose transformations, transform() must return a Stream!
        Stream<String> reversedStringStream = stringStream.transform(new Function<>() {
            @Override
            public String apply(String input) {
                return new StringBuilder(input).reverse().toString();
            }
        });

        // STEP7: Accumulation.
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
    }

    // STEP5: Design the Function interface.
    interface Function<I, O> {
        O apply(I input);
    }
}
