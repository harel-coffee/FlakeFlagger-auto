@Test public void testGetPutPeekRemove(){
  CacheLIRS<Integer,Integer> test=createCache(4);
  test.put(1,10);
  test.put(2,20);
  test.put(3,30);
  assertNull(test.peek(4));
  assertNull(test.getIfPresent(4));
  test.put(4,40);
  verify(test,"mem: 4 stack: 4 3 2 1 cold: non-resident:");
  assertEquals(30,test.getUnchecked(3).intValue());
  assertEquals(20,test.getUnchecked(2).intValue());
  assertEquals(20,test.peek(2).intValue());
  assertEquals(20,test.getUnchecked(2).intValue());
  assertEquals(10,test.peek(1).intValue());
  assertEquals(10,test.getUnchecked(1).intValue());
  verify(test,"mem: 4 stack: 1 2 3 4 cold: non-resident:");
  test.put(3,30);
  verify(test,"mem: 4 stack: 3 1 2 4 cold: non-resident:");
  test.put(5,50);
  verify(test,"mem: 4 stack: 5 3 1 2 cold: 5 non-resident: 4");
  assertEquals(1,test.getMemory(1));
  assertEquals(1,test.getMemory(5));
  assertEquals(0,test.getMemory(4));
  assertEquals(0,test.getMemory(100));
  assertNull(test.peek(4));
  assertNull(test.getIfPresent(4));
  assertEquals(10,test.getUnchecked(1).intValue());
  assertEquals(20,test.getUnchecked(2).intValue());
  assertEquals(30,test.getUnchecked(3).intValue());
  verify(test,"mem: 4 stack: 3 2 1 cold: 5 non-resident: 4");
  assertEquals(50,test.getUnchecked(5).intValue());
  verify(test,"mem: 4 stack: 5 3 2 1 cold: 5 non-resident: 4");
  assertEquals(50,test.getUnchecked(5).intValue());
  verify(test,"mem: 4 stack: 5 3 2 cold: 1 non-resident: 4");
  assertEquals(50,test.remove(5).intValue());
  assertNull(test.remove(5));
  verify(test,"mem: 3 stack: 3 2 1 cold: non-resident: 4");
  assertNull(test.remove(4));
  verify(test,"mem: 3 stack: 3 2 1 cold: non-resident:");
  assertNull(test.remove(4));
  verify(test,"mem: 3 stack: 3 2 1 cold: non-resident:");
  test.put(4,40);
  test.put(5,50);
  verify(test,"mem: 4 stack: 5 4 3 2 cold: 5 non-resident: 1");
  test.getUnchecked(5);
  test.getUnchecked(2);
  test.getUnchecked(3);
  test.getUnchecked(4);
  verify(test,"mem: 4 stack: 4 3 2 5 cold: 2 non-resident: 1");
  assertEquals(50,test.remove(5).intValue());
  verify(test,"mem: 3 stack: 4 3 2 cold: non-resident: 1");
  assertEquals(20,test.remove(2).intValue());
  assertFalse(test.containsKey(1));
  assertNull(test.remove(1));
  assertFalse(test.containsKey(1));
  verify(test,"mem: 2 stack: 4 3 cold: non-resident:");
  test.put(1,10);
  test.put(2,20);
  verify(test,"mem: 4 stack: 2 1 4 3 cold: non-resident:");
  test.getUnchecked(1);
  test.getUnchecked(3);
  test.getUnchecked(4);
  verify(test,"mem: 4 stack: 4 3 1 2 cold: non-resident:");
  assertEquals(10,test.remove(1).intValue());
  verify(test,"mem: 3 stack: 4 3 2 cold: non-resident:");
  test.remove(2);
  test.remove(3);
  test.remove(4);
  test.clear();
  verify(test,"mem: 0 stack: cold: non-resident:");
  test.put(1,10);
  test.put(2,20);
  test.put(3,30);
  test.put(4,40);
  test.put(5,50);
  assertTrue(test.containsValue(50));
  verify(test,"mem: 4 stack: 5 4 3 2 cold: 5 non-resident: 1");
  test.put(1,10);
  verify(test,"mem: 4 stack: 1 5 4 3 2 cold: 1 non-resident: 5");
  assertFalse(test.containsValue(50));
  test.remove(2);
  test.remove(3);
  test.remove(4);
  verify(test,"mem: 1 stack: 1 cold: non-resident: 5");
  assertTrue(test.containsKey(1));
  test.remove(1);
  assertFalse(test.containsKey(1));
  verify(test,"mem: 0 stack: cold: non-resident: 5");
  assertFalse(test.containsKey(5));
  assertTrue(test.isEmpty());
  test.clear();
  test.put(1,10);
  test.put(2,20);
  test.put(3,30);
  test.put(4,40);
  test.put(5,50);
  test.getUnchecked(4);
  test.getUnchecked(3);
  verify(test,"mem: 4 stack: 3 4 5 2 cold: 5 non-resident: 1");
  test.put(6,60);
  verify(test,"mem: 4 stack: 6 3 4 5 2 cold: 6 non-resident: 5 1");
  test.getUnchecked(6);
  verify(test,"mem: 4 stack: 6 3 4 cold: 2 non-resident: 5 1");
}