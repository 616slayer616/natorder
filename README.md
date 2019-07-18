# natorder
[![Build Status](https://travis-ci.org/616slayer616/natorder.svg?branch=master)](https://travis-ci.org/616slayer616/natorder)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=616slayer616_natorder&metric=alert_status)](https://sonarcloud.io/dashboard?id=616slayer616_natorder)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=616slayer616_natorder&metric=bugs)](https://sonarcloud.io/dashboard?id=616slayer616_natorder)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=616slayer616_natorder&metric=code_smells)](https://sonarcloud.io/dashboard?id=616slayer616_natorder)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=616slayer616_natorder&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=616slayer616_natorder)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=616slayer616_natorder&metric=coverage)](https://sonarcloud.io/dashboard?id=616slayer616_natorder)


This project wraps Pierre-Luc Paour's Java implementation of <https://github.com/paour/natorder>, based on Martin Pool <https://github.com/sourcefrog/natsort>.

Issues with the original version have been addressed.


## Licensing
Java Natural Order comparator

org.padler.natorder.NaturalOrderComparator.java -- Perform 'natural order' comparisons of strings in Java.
 Copyright (C) 2003 by Pierre-Luc Paour <natorder@paour.com>

 Based on the C version by Martin Pool, of which this is more or less a straight conversion.
 Copyright (C) 2000 by Martin Pool <mbp@humbug.org.au>

 This software is provided 'as-is', without any express or implied
 warranty.  In no event will the authors be held liable for any damages
 arising from the use of this software.

 Permission is granted to anyone to use this software for any purpose,
 including commercial applications, and to alter it and redistribute it
 freely, subject to the following restrictions:

 1. The origin of this software must not be misrepresented; you must not
 claim that you wrote the original software. If you use this software
 in a product, an acknowledgment in the product documentation would be
 appreciated but is not required.
 2. Altered source versions must be plainly marked as such, and must not be
 misrepresented as being the original software.
 3. This notice may not be removed or altered from any source distribution.

## Usage

### Gradle
```
implementation 'org.padler:natorder:1.0'
```

### Maven
```
<dependency>
  <groupId>org.padler</groupId>
  <artifactId>natorder</artifactId>
  <version>1.0</version>
</dependency>
```

### Integration

```
NaturalOrderComparator naturalOrderComparator = new NaturalOrderComparator();

naturalOrderComparator.compare("file2", "file10");
```
