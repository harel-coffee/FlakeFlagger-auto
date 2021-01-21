@Test public void should_fail_since_actual_can_be_positive_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("\nExpecting:\n <-1.0f>\nto be less than or equal to:\n <0.0f> when comparing values using 'AbsValueComparator'");
  floatsWithAbsValueComparisonStrategy.assertIsNotPositive(someInfo(),-1f);
}