package com.example.e2e.suites;


import com.example.e2e.TestExamples.LoginTestParallel2;
import com.example.e2e.TestExamples.LoginTestsParallel;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({LoginTestsParallel.class, LoginTestParallel2.class})
@IncludeTags("parallel")
@SuiteDisplayName("My Test Suite")
public class TestSuiteExample {
}



/*

MORE EXAMPLES

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.howtodoinjava.junit5.examples.packageA","com.howtodoinjava.junit5.examples.packageB"})
public class JUnit5TestSuiteExample
{
}


----------------------
@IncludePackages Example
-----------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@IncludePackages("com.howtodoinjava.junit5.examples.packageC")
public class JUnit5TestSuiteExample
{
}



--------------------------------
@ExcludePackages Example
--------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@ExcludePackages("com.howtodoinjava.junit5.examples.packageC")
public class JUnit5TestSuiteExample
{
}



------------------------------
@IncludeClassNamePatterns Example
Include all test classes with name ending with ATest or ATests.
-------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@IncludeClassNamePatterns({"^.*ATests?$"})
public class JUnit5TestSuiteExample
{
}



-------------------------------------
@ExcludeClassNamePatterns Example
Exclude all test classes with name ending with ATest or ATests.
--------------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@ExcludeClassNamePatterns({"^.*ATests?$"})
public class JUnit5TestSuiteExample
{
}


------------------------------------------
@IncludeTags Example
This test suite will run all tests tagged with production inside package com.howtodoinjava.junit5.examples (and it’s sub-packages).
-------------------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@IncludeTags("production")
public class JUnit5TestSuiteExample
{
}


-----------------------------------------
@ExcludeTags Example
This test suite will exclude all tests tagged with development inside package com.howtodoinjava.junit5.examples (and it’s sub-packages).
-----------------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@ExcludeTags("development")
public class JUnit5TestSuiteExample
{
}










*/
