package study.reactiveprograming.chapter14;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_13 {

  public static void main(String[] args) throws InterruptedException {
    CryptoCurrencyPriceEmitter priceEmitter = new CryptoCurrencyPriceEmitter();

    Flux.create(
            (FluxSink<Integer> sink) -> priceEmitter.setListener(new CryptoCurrencyPriceListener() {
              @Override
              public void onPrice(List<Integer> priceList) {
                priceList.stream().forEach(price -> sink.next(price));
              }

              @Override
              public void onComplete() {
                sink.complete();
              }
            })).publishOn(Schedulers.parallel())
        .subscribe(data -> log.info("# onNext: {}", data), error -> {
        }, () -> log.info("# onComplete"));

    log.info("# START");
    Thread.sleep(3000);

    log.info("# DELAYED 3s");

    priceEmitter.flowInto();

    log.info("# Changed DATA");

    Thread.sleep(2000);
    log.info("# DELAYED 2s");
    priceEmitter.complete();
    log.info("# DONE");
  }

}

class CryptoCurrencyPriceEmitter {

  private CryptoCurrencyPriceListener listener;

  public void setListener(CryptoCurrencyPriceListener listener) {
    this.listener = listener;
  }

  public void flowInto() {
    listener.onPrice(SampleData.btcPrices);
  }

  public void complete() {
    listener.onComplete();
  }
}

interface CryptoCurrencyPriceListener {

  void onPrice(List<Integer> priceList);

  void onComplete();
}
