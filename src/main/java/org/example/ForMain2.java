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
import java.util.List;

public class ForMain2 {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Simone", "Bordet"),
                new Person("Alfonso", "Carlone")
        );

        List<String> fullNames = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            // Refactor the logic inside the loop into a single method.
            String fullName = fullName(person);
            fullNames.add(fullName);
        }

        System.err.println("fullNames = " + fullNames);
    }

    private static String fullName(Person person) {
        return "%s %s".formatted(person.firstName(), person.lastName());
    }

    public record Person(String firstName, String lastName) {
    }
}
