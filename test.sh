#!/bin/bash
cd doc/examples
for file in *; do
    printf "Testing %20s\toutput: %s\n" "$file" "$(java -jar ../../SimPL.jar $file)"
done

