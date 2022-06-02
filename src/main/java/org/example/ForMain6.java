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

import java.util.List;
import java.util.stream.Collectors;

public class ForMain6 {
    public static void main(String[] args) {
        // This code is the same version of what we designed,
        // but using the Java Stream APIs.

        List<Person> people = List.of(
                new Person("Simone", "Bordet"),
                new Person("Alfonso", "Carlone")
        );

        List<String> result = people.stream()
                .map(person -> fullName(person))
                .map(fullName -> new StringBuilder(fullName).reverse().toString())
                .collect(Collectors.toList());

        System.err.println("result = " + result);
    }

    private static String fullName(Person person) {
        return "%s %s".formatted(person.firstName(), person.lastName());
    }

    public record Person(String firstName, String lastName) {
    }
}
