package yaus

import java.util.concurrent.atomic.AtomicLong

class SequenceGenerator {
    AtomicLong number = new AtomicLong(111111)
    
    Long getNextSequence() {
        return number.incrementAndGet()
    }
}