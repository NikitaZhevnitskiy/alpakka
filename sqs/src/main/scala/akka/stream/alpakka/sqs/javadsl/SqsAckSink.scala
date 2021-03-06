/*
 * Copyright (C) 2016-2018 Lightbend Inc. <http://www.lightbend.com>
 */

package akka.stream.alpakka.sqs.javadsl

import java.util.concurrent.CompletionStage

import akka.Done
import akka.stream.alpakka.sqs.{scaladsl, MessageAction, SqsAckSettings}
import akka.stream.javadsl.Sink
import com.amazonaws.services.sqs.AmazonSQSAsync

import scala.compat.java8.FutureConverters.FutureOps

/**
 * Java API to create acknowledging sinks.
 */
object SqsAckSink {

  /**
   * Creates a sink for a SQS queue using an [[com.amazonaws.services.sqs.AmazonSQSAsync]].
   */
  def create(queueUrl: String,
             settings: SqsAckSettings,
             sqsClient: AmazonSQSAsync): Sink[MessageAction, CompletionStage[Done]] =
    scaladsl.SqsAckSink
      .apply(queueUrl, settings)(sqsClient)
      .mapMaterializedValue(_.toJava)
      .asJava

}
